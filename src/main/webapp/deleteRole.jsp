<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="lang" value="${pageContext.request.locale.language}" />

<!-- Delete Modal HTML -->
<div id="deleteRoleModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">	
		<form action="${context}/roles?action=/deleteRole" method="post">		
			<div class="modal-header">
				<h4 class="modal-title">Delete Role</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to delete these Records?</p>
				<p class="text-warning">
					<small>This action cannot be undone.</small>
				</p>
				<!--   <input type="hidden" name="roleIds" value="...">-->
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> 
				<input id="deleteBtn" name="btnDeleteMultiple" class="btn btn-danger" value="Delete">
			</div>
			</form>
		</div>
	</div>
</div>