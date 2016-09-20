<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <li class="space_width"></li>
    <li class="divider-vertical"></li>

    <li class="dropdown user">
         <a href="#" class="dropdown-toggle avatar" data-toggle="dropdown">
            <img src="<c:url value='assets/images/gear_white.png'/>" alt="sugge">
            <span class="more"><i class="icon16 i-arrow-down-2"></i></span>
        </a>
        <ul class="dropdown-menu" role="menu">
            <!--
            <li role="presentation"><a href="#" class=""><i class="icon16 i-cogs"></i> Settings</a></li>
            <li role="presentation"><a href="profile.html" class=""><i class="icon16 i-user"></i> Profile</a></li>
            -->
            <li role="presentation"><a href="<c:url value="/j_spring_security_logout"/>"><i class="icon16 i-exit"></i> Cerrar sesion</a></li>
        </ul>
    </li>
    <li class="divider-vertical"></li>
