<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <label class="divider-vertical"></label>
        <div id="gama_head" class="head_div">
            <span class="divider-vertical"></span>
            <label>Gamasoft</label>
        </div>

        <div id="opciones_head" class="head_div">
              <div class="dropdown-toggle avatar" data-toggle="dropdown">
                    <span class="icon" ><i class="icon32   i-cog-3"></i></span>
                    <span class="more"><i class="icon16 i-arrow-down-2"></i></span>
               </div>
                <ul class="dropdown-menu" role="menu">
                    <li role="presentation"><a href="#" class=""><i class="icon16 i-cogs"></i> Configuracion</a></li>
                    <li role="presentation"><a href="<c:url value="/j_spring_security_logout"/>" class=""><i class="icon16 i-exit"></i> Salir</a></li>
                </ul>
            </div>
        </div>

        <div id="usuario_head" class="head_div">
            <span class="divider-vertical"></span>
            <label id="session_user"></label>
        </div>
        <div id="rol_head" class="head_div">
            <span class="divider-vertical"></span>
            <label id="session_rol"></label>
        </div>

        </div><!--/.nav-collapse -->
    </nav>
</header> <!-- End #header  -->