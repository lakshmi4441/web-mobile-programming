import {
  inject, TestBed
} from '@angular/core/testing';

import { Todo } from './todo';
import { TodoService } from './todo.service';

describe('Todo Service', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [],
      providers: [TodoService]
    });
  });

  describe('#getAllTodos()', () => {

    it('应该默认返回一个空数组', inject([TodoService], (service: TodoService) => {
      expect(service.getAllTodos()).toEqual([]);
    }));

    it('应该返回所有待办事项', inject([TodoService], (service: TodoService) => {
      const todo1 = new Todo({ value: 'Hello 1', done: false });
      const todo2 = new Todo({ value: 'Hello 2', done: true });
      service.addTodo(todo1);
      service.addTodo(todo2);
      expect(service.getAllTodos()).toEqual([todo2, todo1]);
    }));

  });

  describe('#save(todo)', () => {

    it('应该自动分配一个ID', inject([TodoService], (service: TodoService) => {
      const todo1 = service.addTodo(new Todo({ value: 'Hello 1', done: false }));
      const todo2 = service.addTodo(new Todo({ value: 'Hello 2', done: true }));
      expect(service.getTodoById(todo1.id)).toEqual(todo1);
      expect(service.getTodoById(todo2.id)).toEqual(todo2);
    }));

  });

  describe('#deleteTodoById(id)', () => {

    it('应该删除相应ID的待办事项', inject([TodoService], (service: TodoService) => {
      const todo1 = service.addTodo(new Todo({ value: 'Hello 1', done: false }));
      const todo2 = service.addTodo(new Todo({ value: 'Hello 2', done: true }));
      expect(service.getAllTodos()).toEqual([todo2, todo1]);
      service.deleteTodoById(todo1.id);
      expect(service.getAllTodos()).toEqual([todo2]);
      service.deleteTodoById(todo2.id);
      expect(service.getAllTodos()).toEqual([]);
    }));

    it('如果没有找到使用相应ID的待办事项，则不应删除任何内容', inject([TodoService], (service: TodoService) => {
      const todo1 = service.addTodo(new Todo({ value: 'Hello 1', done: false }));
      const todo2 = service.addTodo(new Todo({ value: 'Hello 2', done: true }));
      expect(service.getAllTodos()).toEqual([todo2, todo1]);
      service.deleteTodoById(3);
      expect(service.getAllTodos()).toEqual([todo2, todo1]);
    }));

  });

  describe('#updateTodoById(id, values)', () => {

    it('应该返回相应ID和更新的数据todo', inject([TodoService], (service: TodoService) => {
      const todo = service.addTodo(new Todo({ value: 'Hello 1', done: false }));
      const updatedTodo = service.updateTodoById(todo.id, {
        value: 'new value'
      });
      expect(updatedTodo.value).toEqual('new value');
    }));

    it('如果未找到待办事项应该返回null', inject([TodoService], (service: TodoService) => {
      const todo = service.addTodo(new Todo({ value: 'Hello 1', done: false }));
      const updatedTodo = service.updateTodoById(2, {
        value: 'new value'
      });
      expect(updatedTodo).toEqual(null);
    }));

  });

  describe('#toggleTodoDone(todo)', () => {

    it('应该返回更新后的待办事项与完成状态', inject([TodoService], (service: TodoService) => {
      const todo = new Todo({ value: 'Hello 1', done: false });
      service.addTodo(todo);
      const updatedTodo = service.toggleTodoDone(todo);
      expect(updatedTodo.done).toEqual(true);
      service.toggleTodoDone(todo);
      expect(updatedTodo.done).toEqual(false);
    }));

  });

});
