<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="lang" value="${pageContext.request.locale.language}" />

<div id="addRoleModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
	     <form action="${context}/roles?action=/addRole" method="post">
			<!--<form action="/AppEv/addRole" method="post">-->
				<div class="modal-header">
					<h4 class="modal-title">Ajouter Un Nouveau Role</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Nom Du Role:</label> 
						<input name="name" type="text" class="form-control" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> 
					<input type="submit" class="btn btn-success" value="Add">
				</div>
			</form>
		</div>
	</div>
</div>