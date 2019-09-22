import { Component } from '@angular/core';
import { TodoService } from './todo.service';
import { Todo } from './todo';

@Component({
  selector: 'app-todo-app',
  templateUrl: './todo-app.component.html',
  styleUrls: ['./todo-app.component.scss'],
  providers: [TodoService]
})
export class TodoAppComponent {
  newTodo: string = '';
  constructor(
    private todoService: TodoService
  ) { }

  // /**
  //  * add todo
  //  * @memberof TodoAppComponent
  //  */
  addTodo(): void {
    if (!this.newTodo) {
      return alert('What do you need to write?');
    }
    this.todoService.addTodo(new Todo({
      value: this.newTodo
    }));
    this.newTodo = '';
  }

  destroyTodo(todo: Todo): void {
    this.todoService.deleteTodoById(todo.id);
  }

  destroyAllTodo(): void {
    if (!this.clearCount) {
      return;
    }
    if (!confirm('Do you need to delete the selected one?')) {
      return;
    }
    this.todoService.deleteAllTodo();
  }

  toggleDoneTodo(todo: Todo): void {
    this.todoService.toggleTodoDone(todo);
  }

  toggleAllTodoDone(event: boolean): void {
    this.todos.forEach(item => item.done = event);
  }


  editingTodo(todo: Todo): void {
    if (!todo.done) {
      todo.edit = true;
    }
  }

  cancelEditingTodo(todo: Todo): void {
    todo.edit = false;
  }

  editedTodo(todo: Todo, input: HTMLInputElement): void {
    todo.value = input.value;
    todo.edit = false;
  }

  get todos(): Todo[] {
    return this.todoService.getAllTodos();
  }

  get allDone(): boolean {
    const todos = this.todos;
    return todos.length && todos.filter(item => item.done).length === todos.length;
  }

  get todoCount(): number {
    return this.todos.filter(item => !item.done).length;
  }

  get clearCount(): number {
    return this.todos.filter(item => item.done).length;
  }

}
