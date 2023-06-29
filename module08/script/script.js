
const input = document.getElementById('task-input');
const addTaskBtn = document.getElementById('add-task-btn');
const taskList = document.getElementById('task-list');

function addTask() {

  if (input.value.trim() !== '') {

    const newTask = document.createElement('li');

    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    newTask.appendChild(checkbox);

    newTask.append(document.createTextNode(input.value));

    const deleteBtn = document.createElement('button');
    deleteBtn.innerText = 'Удалить';
    deleteBtn.addEventListener('click', function() {

      taskList.removeChild(newTask);
    });
    newTask.appendChild(deleteBtn);

    taskList.appendChild(newTask);

    input.value = '';
  }
}

addTaskBtn.addEventListener('click', addTask);