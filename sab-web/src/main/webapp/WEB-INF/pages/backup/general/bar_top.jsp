<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
    <div class="navbar navbar-inner navbar-inverse navbar-fixed-top">
        <div id="progress_bar"></div>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <a class="navbar-brand" href="http://www.gamasoftcol.com"><img src="<c:url value='assets/images/gamasoft_logo4.png'/>" alt="Gamasoft" class="img-responsive"></a>
            <button type="button" class="navbar-toggle btn-danger" data-toggle="collapse" data-target="#navbar-to-collapse">
                <span class="sr-only">menu derecho</span>
                <i class="icon16 i-arrow-8"></i>
            </button>          
            <div class="collapse navbar-collapse" id="navbar-to-collapse">
                 <ul class="nav navbar-nav pull-right">
                    <jsp:include page="elements_bar/user_tools.jsp"/>
                 </ul>
                <span id="mi_user"></span>
            </div>
        </nav>
    </div>
</div>