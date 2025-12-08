<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit Car">
    <h1>Edit Car</h1>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar"  >
        <div class="form-group">
            <label for="license_plate">License plate</label>
            <input type="text" id="license_plate" name="license_plate" class="form-control" placeholder="" value="${car.licensePlate}" required />
            <div class="invalid-feedback">Please provide a license plate.</div>
        </div>

        <div class="form-group">
            <label for="parking_spot">Parking spot</label>
            <input type="text" id="parking_spot" name="parking_spot" class="form-control" value="${car.parkingSpot}" required />
            <div class="invalid-feedback">Please provide a parking spot.</div>
        </div>

        <div class="form-group">
            <label for="owner_id">Owner</label>
            <select id="owner_id" name="owner_id" class="form-control" required >
                <option value="">Choose...</option>
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}"${car.ownerName eq user.name ? 'selected': ''}>${user.name} </option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">Please choose an owner.</div>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <input type="hidden" name="car_id" value="${car.id}" />
    </form>
</t:pageTemplate>