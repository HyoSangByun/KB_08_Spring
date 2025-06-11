<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
    <style>
        .done{
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>📋 Todo List</h1>

<div>
    <h2>할 일 추가</h2>
    <form action="/todo/insert" method="post">
        <p>
            제목 : <input type="text" name="title">
        </p>
        <p>
            내용 : <textarea name="description" rows="3" cols="50"></textarea>
        </p>
        <button>할 일 추가</button>
    </form>
</div>

<!-- Todo 목록 -->
<div>
    <h2>할 일 목록 (총 ${todos.size()}개)</h2>

    <c:if test="${not empty todos}">
        <table border="1">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>설명</th>
                <th>완료여부</th>
                <th>삭제</th>
            </tr>
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.id}</td>
                    <td>${todo.title}</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty todo.description}">-</c:when>
                            <c:otherwise>${todo.description}</c:otherwise>
                        </c:choose>
                    </td>
                    <td class="done" data-id="${todo.id}">
                        <c:choose>
                            <c:when test="${todo.done}">✅ 완료</c:when>
                            <c:otherwise>❌ 미완료</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <button type="button" class="delete-btn" data-id="${todo.id}">삭제</button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty todos}">
        <p>등록된 할 일이 없습니다.</p>
    </c:if>
</div>

<form id="hiddenForm" name="hiddenForm" method="post">
    <input type="hidden" name="id">
</form>

<script>
    const hiddenForm = document.hiddenForm;
    const hiddenId = hiddenForm.querySelector("input");

    // 완료 여부 클릭 시
    document.querySelectorAll(".done").forEach(item => {
        const id = item.dataset.id;

        item.addEventListener("click", () => {
            hiddenForm.action = "/todo/update"
            hiddenId.value = id;
            hiddenForm.submit();
        })
    });

    // 삭제 여부 클릭 시
    document.querySelectorAll(".delete-btn").forEach(item => {
        const id = item.dataset.id;

        item.addEventListener("click", () => {
            hiddenForm.action = "/todo/delete"
            hiddenId.value = id;
            hiddenForm.submit();
        })

    });
</script>
</body>
</html>
