<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
            </div>

            <div class="modal-body">
                <p>You are about to delete one track, this procedure is irreversible.</p>
                <p>Do you want to proceed?</p>
                <p class="debug-url"></p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a class="btn btn-danger btgay.gasparyan91@gmail.comn-ok">Delete</a>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
        <table class="table table-striped custab">
            <thead>
            <a href="/addLesson" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new Lesson</a>
            <tr>
                <th>name</th>
                <th>description</th>
                <th class="text-center">Action</th>
            </tr>
            </thead>

            <c:forEach var="lesson" items="${requestScope.get('lessons')}">

                <tr>
                    <td><c:out value="${lesson.getName()}" />
                    </td>
                    <td><c:out value="${lesson.description}" />
                    </td>
                    <td><a href="/singleLesson?id=<c:out value="${lesson.id}" />">Show more</a> | <a
                            href="/updateLesson?id=<c:out value="${lesson.id}" />">Update</a> |
                        <a href="#"
                           data-href="/deleteLesson?id=<c:out value="${lesson.id}" />"
                           data-toggle="modal"
                           data-target="#confirm-delete">Delete</a></td>
                </tr>

            </c:forEach>

        </table>
    </div>
</div>

<script>
    $('#confirm-delete').on('show.bs.modal', function(e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
    });
</script>

