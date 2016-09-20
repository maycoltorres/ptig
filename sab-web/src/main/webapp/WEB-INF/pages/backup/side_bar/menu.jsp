<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="side-options">
	    <ul>
	        <li><a href="#" id="collapse-nav" class="act act-primary tip" title="Collapse navigation"><i class="icon64 i-arrow-left-7"></i></a></li>
	    </ul>
	</div>
	<div class="sidebar-wrapper">
	    <nav id="mainnav">
	        <ul class="nav nav-list">
	            <li class="mustHidden lista_cliente_act">
	                <a href="#clientes">
	                    <span class="icon"><i class="icon16  i-user-3"></i></span>
	                    <span class="txt">Clientes</span>
	                </a>
	            </li>
    <!--
                <li>
                    <a href="#puntos">
                        <span class="icon"><i class="icon32  i-globe-3"></i></span>
                        <span class="txt">Puntos</span>
                    </a>
                </li>
    -->
	            <li class="mustHidden lista_grupo_act">
	                <a href="#grupos">
	                    <span class="icon"><i class="icon16 i-users-4"></i></span>
	                    <span class="txt">Grupos</span>
	                </a>
	            </li>
    			<li class="mustHidden lista_marca_act">
	                <a href="#marcas">
	                    <span class="icon"><i class="icon16 i-wave"></i></span>
	                    <span class="txt">Marcas</span>
	                </a>
	            </li>

    			<li class="mustHidden lista_usuario_act">
	                <a href="#usuarios">
	                    <span class="icon"><i class="icon16  i-user-7"></i></spanNc>
	                    <span class="txt">Usuarios</span>
	                </a>
	            </li>
                <li class="mustHidden lista_rol_act">
                    <a href="#roles">
                        <span class="icon"><i class="icon16   i-shield"></i></span>
                        <span class="txt">Roles</span>
                    </a>
                </li>
                <li class="mustHidden lista_rolCliente_act">
                    <a href="#mis_roles">
                        <span class="icon"><i class="icon16  i-vcard"></i></span>
                        <span class="txt">Mis roles</span>
                    </a>
                </li>
                <li class="mustHidden lista_articulo_act">
                    <a href="#articulos">
                        <span class="icon"><i class="icon16 i-cart-6"></i></span>
                        <span class="txt">Articulos</span>
                    </a>
                </li>
                <!--
                <li>
                    <a href="#permisos">
                        <span class="icon"><i class="icon16  i-tree-3"></i></span>
                        <span class="txt">Permisos</span>
                    </a>
                </li>
                -->

                <!---
	            <li>
	                <a href="#">
	                    <span class="icon"><i class="icon20 i-table-2"></i></span>
	                    <span class="txt">Tables</span>
	                </a>
	                <ul class="sub">
	                    <li>
	                        <a href="tables.html">
	                            <span class="icon"><i class="icon20 i-table"></i></span>
	                            <span class="txt">Static tables</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="data-tables.html">
	                            <span class="icon"><i class="icon20 i-table"></i></span>
	                            <span class="txt">Data tables</span>
	                        </a>
	                    </li>
	                </ul>
	            </li>
	            <li>
	                <a href="grid.html">
	                    <span class="icon"><i class="icon20 i-grid-5"></i></span>
	                    <span class="txt">Grid</span>
	                </a>
	            </li>
	            <li>
	                <a href="typo.html">
	                    <span class="icon"><i class="icon20 i-font"></i></span>
	                    <span class="txt">Typography</span>
	                </a>
	            </li>
	            <li>
	                <a href="calendar.html">
	                    <span class="icon"><i class="icon20 i-calendar"></i></span>
	                    <span class="txt">Calendar</span>
	                </a>
	            </li>
	            <li>
	                <a href="#">
	                    <span class="icon"><i class="icon20 i-cogs"></i></span>
	                    <span class="txt">Ui Elements</span>
	                </a>
	                <ul class="sub">
	                    <li>
	                        <a href="icons.html">
	                            <span class="icon"><i class="icon20 i-IcoMoon"></i></span>
	                            <span class="txt">Icons</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="buttons.html">
	                            <span class="icon"><i class="icon20 i-point-up"></i></span>
	                            <span class="txt">Buttons</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="ui-elements.html">
	                            <span class="icon"><i class="icon20 i-puzzle"></i></span>
	                            <span class="txt">UI Elements</span>
	                        </a>
	                    </li>
	                </ul>
	            </li>
	            <li>
	                <a href="gallery.html">
	                    <span class="icon"><i class="icon20 i-images"></i></span>
	                    <span class="txt">Gallery</span>
	                </a>
	            </li>
	            <li>
	                <a href="maps.html">
	                    <span class="icon"><i class="icon20 i-location-4"></i></span>
	                    <span class="txt">Maps</span>
	                </a>
	            </li>
	            <li>
	                <a href="file-manager.html">
	                    <span class="icon"><i class="icon20 i-cloud-upload"></i></span>
	                    <span class="txt">File manager</span>
	                </a>
	            </li>
	            <li>
	                <a href="widgets.html">
	                    <span class="icon"><i class="icon20 i-cube-3"></i></span>
	                    <span class="txt">Widgets</span>
	                </a>
	            </li>
	            <li>
	                <a href="#">
	                    <span class="icon"><i class="icon20 i-file-8"></i></span>
	                    <span class="txt">Pages</span>
	                </a>
	                <ul class="sub">
	                    <li>
	                        <a href="#">
	                            <span class="icon"><i class="icon20 i-warning"></i></span>
	                            <span class="txt">Error Pages</span>
	                        </a>
	                        <ul class="sub">
	                            <li>
	                                <a href="403.html">
	                                    <span class="icon"><i class="icon20 i-notification"></i></span>
	                                    <span class="txt">Error 403</span>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="404.html">
	                                    <span class="icon"><i class="icon20 i-notification"></i></span>
	                                    <span class="txt">Error 404</span>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="405.html">
	                                    <span class="icon"><i class="icon20 i-notification"></i></span>
	                                    <span class="txt">Error 405</span>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="500.html">
	                                    <span class="icon"><i class="icon20 i-notification"></i></span>
	                                    <span class="txt">Error 500</span>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="503.html">
	                                    <span class="icon"><i class="icon20 i-notification"></i></span>
	                                    <span class="txt">Error 503</span>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="offline.html">
	                                    <span class="icon"><i class="icon20 i-notification"></i></span>
	                                    <span class="txt">Offline page</span>
	                                </a>
	                            </li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="invoice.html">
	                            <span class="icon"><i class="icon20 i-credit"></i></span>
	                            <span class="txt">Invoice page</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="profile.html">
	                            <span class="icon"><i class="icon20 i-user"></i></span>
	                            <span class="txt">Profile page</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="search.html">
	                            <span class="icon"><i class="icon20 i-search-2"></i></span>
	                            <span class="txt">Search page</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="email.html">
	                            <span class="icon"><i class="icon20 i-envelop-2"></i></span>
	                            <span class="txt">Email page</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="support.html">
	                            <span class="icon"><i class="icon20 i-support"></i></span>
	                            <span class="txt">Support page</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="faq.html">
	                            <span class="icon"><i class="icon20 i-question"></i></span>
	                            <span class="txt">FAQ page</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="blank.html">
	                            <span class="icon"><i class="icon20 i-file-7"></i></span>
	                            <span class="txt">Blank page</span>
	                        </a>
	                    </li>
	                </ul>
	            </li>
    -->
	        </ul>
	    </nav> <!-- End #mainnav -->
	</div> <!-- End .sidebar-wrapper  -->