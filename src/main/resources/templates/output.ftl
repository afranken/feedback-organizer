<#ftl/>
<#-- @ftlvariable name="room" type="com.github.afranken.feedback.Room" -->
<#-- @ftlvariable name="slotsToPairs" type="java.util.Map<com.github.afranken.feedback.Slot, java.util.List<com.github.afranken.feedback.Pair>>" -->
<#-- @ftlvariable name="pair" type="com.github.afranken.feedback.Pair" -->
<#setting url_escaping_charset='UTF-8'>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="bootstrap.min.css"/>
</head>
<body>
<h1>Feedback</h1>
<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <#list rooms as room>
        <th>${room.name}</th>
        </#list>
    </tr>
    </thead>
    <tbody>
    <#list slotsToPairs?keys as slot>
    <tr>
        <th scope="row">${slot.name}</th>
        <#list slotsToPairs(slot) as pair>
        <td>${pair.getRenderedName()}</td>
        </#list>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>