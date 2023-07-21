package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.HttpMethod;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import model.Task;
import model.TaskDto;
import repository.TaskRepositoryImpl;
import taskService.TaskServiceImpl;

/**
 * The TaskServlet class is a servlet that handles HTTP requests for managing tasks. It provides methods for handling
 * GET, POST, and DELETE requests, as well as basic parameter validation. This class uses the TaskServiceImpl and
 * TaskRepositoryImpl classes for performing CRUD operations on tasks. It also utilizes the Jackson ObjectMapper for
 * converting JSON data to TaskDto objects and vice versa. The servlet is registered with the "/task" URL pattern.
 */

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    private TaskServiceImpl taskService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Initializes the servlet by setting up the task service.
     *
     * @param config the ServletConfig object containing the servlet's configuration and initialization parameters
     * @throws ServletException if a servlet exception occurs during initialization
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.taskService = new TaskServiceImpl(new TaskRepositoryImpl());
    }

    /**
     * Handles HTTP GET requests by calling the doGetOrDelete method with the HttpMethod.GET parameter.
     *
     * @param req the HttpServletRequest object containing the request parameters and attributes
     * @param resp the HttpServletResponse object used to send the response
     * @throws IOException if an input or output exception occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGetOrDelete(req, resp, HttpMethod.GET);
    }

    /**
     * Handles HTTP POST requests by creating a new task based on the JSON request body and saving it.
     *
     * @param req the HttpServletRequest object containing the request parameters and attributes
     * @param resp the HttpServletResponse object used to send the response
     * @throws IOException if an input or output exception occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonRequestBody = req.getReader().lines()
                .collect(Collectors.joining());
        TaskDto taskDto = objectMapper.readValue(jsonRequestBody, TaskDto.class);
        Task task = taskService.postTask(taskDto);
        objectMapper.writeValueAsString(task);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(objectMapper.writeValueAsString(task));
        System.out.println("Задача: " + task + " сохранена.");
    }

    /**
     * Handles HTTP DELETE requests by delegating the request handling to the doGetOrDelete method
     * with the specified HttpMethod.
     *
     * @param req the HttpServletRequest object containing the request parameters and attributes
     * @param resp the HttpServletResponse object used to send the response
     * @throws IOException if an input or output exception occurs
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGetOrDelete(req, resp, HttpMethod.DELETE);
    }

    /**
     * Handles GET or DELETE HTTP requests.
     *
     * @param req         An {@link HttpServletRequest} object that contains the request the client has made of the
     * servlet.
     * @param resp        An {@link HttpServletResponse} object that contains the response the servlet sends to the
     * client.
     * @param httpMethod  An enum representing the HTTP method, can be either GET or DELETE.
     * @throws IOException if an input or output error is detected when the servlet handles the GET or DELETE request.
     *
     * <p> If the HTTP method is GET, fetches a task identified by the UUID from the task service.
     * If the HTTP method is DELETE, deletes a task identified by the UUID from the task service.</p>
     *
     * <p> This method first validates incoming parameters, then gets the UUID by key "id".
     * Based on the provided HttpMethod it performs specific operation (GET or DELETE).
     * Resulting task is then serialized to a JSON object and written to the response.</p>
     */
    private void doGetOrDelete(HttpServletRequest req, HttpServletResponse resp, HttpMethod httpMethod)
            throws IOException {
        Task task = null;
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parametrMap = req.getParameterMap();
        validateParameters(parametrMap);
        String[] id = parametrMap.get("id");
        UUID uuid = UUID.fromString(id[0]);
        if (httpMethod.equals(HttpMethod.GET)) {
            task = taskService.getTask(uuid);
        }
        if (httpMethod.equals(HttpMethod.DELETE)) {
            task = taskService.deleteTask(uuid);
            System.out.println("Задача: " + task + " удалена.");
        }
        String taskJson = objectMapper.writeValueAsString(task);
        writer.write(taskJson);
    }

    /**
     *
     * Validates the request parameters to ensure they meet the necessary criteria.
     *
     * @param param the Map object containing the request parameters
     * @throws RuntimeException if the parameters are not valid
     */
    private void validateParameters(Map<String, String[]> param) {
        if (param.isEmpty()) {
            throw new RuntimeException("id задачи для получения не указан.");
        }
        if (param.size() > 1) {
            throw new RuntimeException("Укажите только один id задачи.");
        }
        if (!param.containsKey("id")) {
            throw new RuntimeException("Указано не правильное название параметра.");
        }
    }
}
