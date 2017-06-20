package com.tdr3.hs.rest.dlb;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.tdr3.hs.bean.UserInfo;
import com.tdr3.hs.dlb.task.DLBTaskStatus;
import com.tdr3.hs.dlb.todo.Todo;
import com.tdr3.hs.service.dlb.TodoService;

/**
 * Provides rest services for DLB TODOs
 *
 */
@Path("/todo")
public class DlbTodoService {
   
    @Autowired
    UserInfo ui;
   
    @Autowired
    TodoService todoService;


    /**
     * Get a list of TODOs assigned to the current user
     *
     * @return
     */
    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public TodoListDTO todo(@QueryParam("creator") @DefaultValue("false") String creator) {

        // If creator is true get all tickets that were created by the user
        boolean useCreator = Boolean.valueOf(creator);
       
        List<Todo> list = null;
       
        if (!useCreator) {
            // Get all tickets assigned to the user
            list = todoService.getTodosByAssignee(ui.getClient().getId(), ui.getEmployee().getId());
        }
        else {
            // All tickets that were created by the user
            list = todoService.getTodosByCreator(ui.getClient().getId(), ui.getEmployee().getId());
        }
       
        return TodoListDTO.create(list);
    }
   
    /**
     * Delete a Todo or a recurring Todo.  If the query parameter recurring is true, the todoId is treated as the
     * recurring id instead 
     *
     * @param todoId
     * @param recuring
     * @return
     */
    @DELETE
    @Path("/{todoId}")
    public Response deleteTodo(@PathParam("todoId") int todoId, @QueryParam("recurring") @DefaultValue("false") String recurring) {
        boolean isRecurring = Boolean.valueOf(recurring);
       
        boolean success = false;
        if (isRecurring) {
            success = todoService.deleteRecurringTodo(ui.getClient().getId(), todoId);
        }
        else {
            success = todoService.deleteTodo(ui.getClient().getId(), todoId);
        }
       
        if (success) {
            return Response.ok().build();
        }
       
        return Response.status(Response.Status.NOT_FOUND).build();
    }
   
    /**
     * Update the status of a single TODO
     *
     * @param todoId
     * @param todo
     * @return
     */
    @PUT
    @Path("/{todoId}/status")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateStatus(@PathParam("todoId") int todoId, TodoItemDTO todo) {

        // check to see if status is valid
        try {
            @SuppressWarnings("unused")
            DLBTaskStatus status = DLBTaskStatus.values()[todo.getStatus()];
        }
        catch (Exception exp) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
       
        boolean success =
                todoService.updateStatus(ui.getClient().getId(), ui.getEmployee().getId(), todoId, todo.getStatus());
       
        if (success) {
            return Response.ok().build();
        }
       
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}