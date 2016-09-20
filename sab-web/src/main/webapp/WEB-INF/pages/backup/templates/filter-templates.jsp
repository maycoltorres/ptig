<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="FilterPaneTemplate" type="text/template">
      <!--<p>Date: <input type="text" id="datepicker" /></p>-->
    <p>Location: <select id="LOCATION_PENETRATION_FILTER" >
          <option label="Andean" value="29">Andean</option>  
          <option label="Antigua and Barbuda" value="19">Antigua and Barbuda</option>  
          <option label="Argentina" value="7">Argentina</option> 
          <option label="Aruba" value="20">Aruba</option>  
          <option label="Bahamas" value="21">Bahamas</option>  
          <option label="Barbados" value="22">Barbados</option>  
          <option label="Belize" value="16">Belize</option>  
          <option label="Bermuda" value="24">Bermuda</option>  
          <option label="Bolivia" value="6">Bolivia</option> 
          <option label="Brazil_" value="35">Brazil</option>
          <option label="Caribbean" value="17">Caribbean</option>  
          <option label=" America America" value="9"> America America</option> 
          <option label="Chile" value="5">Chile</option> 
          <option label="Colombia" value="32">Colombia</option>  
          <option label="Costa Rica" value="12">Costa Rica</option>  
          <option label="Cruise Ships" value="37">Cruise Ships</option>  
          <option label="Cruise Ships/US Military" value="36">Cruise Ships/US Military</option>  
          <option label="Dominican Republic" value="25">Dominican Republic</option>
          <option label="Ecuador" value="31">Ecuador</option>
          <option label="El Salvador" value="11">El Salvador</option>
          <option label="Guatemala" value="15">Guatemala</option>
          <option label="Honduras" value="10">Honduras</option>
          <option label="Jamaica" value="23">Jamaica</option>
          <option selected label="Latin America" value="1">Latin America</option>
          <option label="Malvinas" value="39">Malvinas</option>
          <option label="Mexico_" value="34">Mexico</option>
          <option label="Netherlands Antillesa" value="18">Netherlands Antilles</option>
          <option label="Nicaragua" value="14">Nicaragua</option>
          <option label="Nothern Latin America" value="8">Nothern Latin America</option>
          <option label="Other Caribbean" value="28">Other Caribbean</option>
          <option label="Panama" value="13">Panama</option>
          <option label="Paraguay" value="4">Paraguay</option>
          <option label="Peru" value="30">Peru</option>
          <option label="Puerto Rico" value="26">Puerto Rico</option>
          <option label="Southern Cone" value="2">Southern Cone</option>
          <option label="Trinidad and Tobago" value="27">Trinidad and Tobago</option>
          <option label="Uruguay" value="3">Uruguay</option>
          <option label="US Military" value="38">US Military</option>
          <option label="Venezuela" value="33">Venezuela</option>
           </select>
    </p>
     <p>Year: <select id="YEAR_FILTER" >
            <option selected value="2012">2012</option>
            <option value="2013">2013</option>
           </select>
      </p>

    <p>Rating Location: <select id="ratinglocationpicker" >
          <option name="Argentina" value="1">Argentina</option>
          <option selected name="Brazil" value="2">Brazil</option>
          <option name="Mexico" value="3">Mexico</option>
          <option Name="Chile" value="4">Chile</option>
          <option Name="Chile" value="5">Colombia</option>
          <option Name="Chile" value="6">Peru</option>
          <option Name="Chile" value="7">Central America</option>
          <option Name="Chile" value="50">Venezuela</option>
          <option Name="Chile" value="100">MC09</option>
          <option Name="Chile" value="101">MC08</option>
           </select>
    </p>

    <p>Date: <select id="ratingdate" >
          <option selected value="1-2012">January (2012)</option>
          <option value="2-2012">Febraury (2012)</option>
          <option value="3-2012">March (2012)</option>
          <option value="4-2012">April (2012)</option>
          <option value="5-2012">May (2012)</option>
          <option value="6-2012">June (2012)</option>
          <option value="7-2012">July (2012)</option>
          <option value="8-2012">August (2012)</option>
          <option value="9-2012">September (2012)</option>
          <option value="10-2012">October (2012)</option>
          <option value="11-2012">November (2012)</option>
          <option value="12-2012">December (2012)</option>
           </select>
    </p>
    <p>Channels: <select multiple id="ratingchannel" >
          <option value="A&E">A&E</option>
          <option value="Animal Planet">Animal Planet</option>
          <option value="AXN">AXN</option>
          <option value="Boomerang">Boomerang</option>
          <option selected value="Cartoon Network">Cartoon Network</option>
          <option selected value="Discovery Channel">Discovery Channel</option>
          <option value="Disney Channel">Disney Channel</option>
          <option value="History">History</option>
          <option value="National Geographic">National Geographic</option>
          <option value="Nickelodeon">Nickelodeon</option>
           </select>
    </p>
  
    <p><button class="btn btn-inverse" id="filterbutton"   >Apply</button></p>
    </div>
</script>