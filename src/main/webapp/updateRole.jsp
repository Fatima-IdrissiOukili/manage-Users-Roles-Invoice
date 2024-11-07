<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="lang" value="${pageContext.request.locale.language}" />

<div id="editRoleModal" class="modal fade" ng-app="roleApp" ng-controller="roleCtrl">
	<div class="modal-dialog">
		<div class="modal-content">			
			<form action="${context}/roles?action=/updateRole" method="post">
			<!--<form action="/AppEv/updateRole" method="POST">-->
				
				<div class="modal-header">
					<h4 class="modal-title">Edit Role</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Name</label>
						<input name="name" ng-model="role.name" type="text" class="form-control" required>
					</div>
					
				</div>
				<div class="modal-footer">
					<input type="hidden" name="id" value="{{role.id}}"/>
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> 
					<input type="submit" class="btn btn-info" value="Update">
				</div>
			</form>
		</div>
	</div>
</div>

