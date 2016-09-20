<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <div class="tableRef hidden">
        <i class="icon32  i-close-3 pull-right"></i>
        <div class="panel-body">
            <div id="container_slide" class="panel">
                <div class="slide">
                    <h4 class="title_color">Unidades de peso</h4>
                    <form>
                        <table class="">
                              <thead>
                                    <tr>
                                        <th class="centrate">Cantidad</th>
                                        <th class="centrate">Unidad</th>
                                        <th class="centrate">Miligramo</th>
                                        <th class="centrate">Gramo</th>
                                        <th class="centrate">Libra</th>
                                        <th class="centrate">kilogramo</th>
                                    </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="centrate">1</td>
                                    <td>kilogramo</td>
                                    <td class="centrate">1000000</td>
                                    <td class="centrate">1000</td>
                                    <td class="centrate">2.20</td>
                                    <td class="centrate">1</td>
                                </tr>
                                <tr>
                                    <td class="centrate">1</td>
                                    <td>Gramo</td>
                                    <td class="centrate">1000000</td>
                                    <td class="centrate">1</td>
                                    <td class="centrate">453.6</td>
                                    <td class="centrate">1000</td>
                                </tr>
                                <tr>
                                    <td class="centrate">1</td>
                                    <td>Miligramo</td>
                                    <td class="centrate">1</td>
                                    <td class="centrate">0.001</td>
                                    <td class="centrate">0.000002205</td>
                                    <td class="centrate">0.000001</td>
                                </tr>
                                <tr>
                                    <td class="centrate">1</td>
                                    <td>Libra</td>
                                    <td class="centrate">4536000</td>
                                    <td class="centrate">453.60</td>
                                    <td class="centrate">1</td>
                                    <td class="centrate">0.45</td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="slide">
                    <h4 class="title_color">Unidades de volumen</h4>
                    <form>
                        <table class="">
                            <thead>
                            <tr>
                                <th class="centrate">Cantidad</th>
                                <th class="centrate">Unidad</th>
                                <th class="centrate">Galon</th>
                                <th class="centrate">Litro</th>
                                <th class="centrate">mililitro</th>
                                <th class="centrate">Centimetro cubico</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="centrate">1</td>
                                <td>Galon</td>
                                <td class="centrate">1</td>
                                <td class="centrate">3.785</td>
                                <td class="centrate">3785</td>
                                <td class="centrate">3785</td>
                            </tr>
                            <tr>
                                <td class="centrate">1</td>
                                <td>Litro</td>
                                <td class="centrate">0.2642</td>
                                <td class="centrate">1</td>
                                <td class="centrate">1000</td>
                                <td class="centrate">1000</td>
                            </tr>
                            <tr>
                                <td class="centrate">1</td>
                                <td>Mililitro</td>
                                <td class="centrate">0.0002642</td>
                                <td class="centrate">0.001</td>
                                <td class="centrate">1</td>
                                <td class="centrate">1</td>
                            </tr>
                            <tr>
                                <td class="centrate">1</td>
                                <td>Centimetro cubico</td>
                                <td class="centrate">0.0002642</td>
                                <td class="centrate">0.001</td>
                                <td class="centrate">1</td>
                                <td class="centrate">1</td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="slide">
                    <h4 class="title_color">Unidades de longitud</h4>
                    <form>
                        <table class="">
                            <thead>
                            <tr>
                                <th class="centrate">Cantidad</th>
                                <th class="centrate">Unidad</th>
                                <th class="centrate">Metro</th>
                                <th class="centrate">Centimetro</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="centrate">1</td>
                                <td>Metro</td>
                                <td class="centrate">1</td>
                                <td class="centrate">100</td>
                            </tr>
                            <tr>
                                <td class="centrate">1</td>
                                <td>Centimetro</td>
                                <td class="centrate">100</td>
                                <td class="centrate">1</td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div id="confirmar" class="panel panel-default">
        <div class="panel-heading">
            <h4>Confirmar accion </h4>
        </div>
        <div class="panel-body">
            <span>Esta seguro de <span id="confirmar_action"></span> este registro?</span>
            <br>
            <span id="prompt_message"></span>
        </div>
        <div id="footer_btn">
            <input id="confirmar_cancelar" class="btn btn-sm btn-success" type="button" value="Cancelar">
            <input id="confirmar_seguro" class="btn btn-sm btn-danger" type="button" value="Estoy seguro">
        </div>
    </div>
    <div id="shadow_black">
    </div>
    <div id="shadow_white" class="box_show rounded">
        <label id="charge_label_gama" >Gamasoft</label>
        </br>
        <span id="charge_label_number" >1%</span>
        </br>
        <label id="charge_label_loading" >Cargado</label>

        <div class="progress_charge"></div>
    </div>

    <div id="connection" class="panel panel-default">
        <div class="panel-heading">
            <h4>Fallo de conexion</h4>
        </div>
        <div class="panel-body">
            <div>
                <label>&lrm;</label>
                <img id="connection_image" src="<c:url value='assets/images/web.png'/>"/>
                <img id="connection_cargando" class="hidden" src="<c:url value='assets/images/loader.gif'/>"/>
            </div>
            <span id="connection_text"></span>
        </div>
        <div id="footer_btn">
            <input id="tryConnection" class="btn btn-lg login" type="button" value="Verificar conexion">
        </div>
    </div>
    <div id="sesion_div" class="panel panel-default">
        <iframe id="iframe_sesion">

        </iframe>
    </div>
    <div id="message">

    </div>
    <div id="content_floating" class="panel panel-default floating_form">
        <div class="panel-heading">
            <i class="icon32  i-close-3 pull-right"></i>
            <h3 class="centrate"></h3>
        </div>
        <div class="panel-body"></div>
    </div>
<jsp:include page="header.jsp"/>
<div class="main">
    <jsp:include page="lateral.jsp"/>
    <jsp:include page="content.jsp"/>
</div>
<jsp:include page="footer.jsp"/>

</body>