<%@ page import="todo.TaskList" %>



<div class="fieldcontain ${hasErrors(bean: taskListInstance, field: 'listName', 'error')} ">
	<label for="listName">
		<g:message code="taskList.listName.label" default="List Name" />
		
	</label>
	<g:textField name="listName" value="${taskListInstance?.listName}"/>
</div>

