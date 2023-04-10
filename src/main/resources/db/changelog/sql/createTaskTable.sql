CREATE TABLE task(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    time TIME,
    finished BOOLEAN,
    toDoList_id INT,
    CONSTRAINT fk_toDoTasks_id FOREIGN KEY (toDoList_id) REFERENCES task(id)
)