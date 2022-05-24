// 注销操作
$(document).on("click", ".logout", function () {
    localStorage.clear()
    window.location.replace("/login")
})

// 跳转个人信息
$(document).on("click", ".info", function () {
    window.location.replace("/info")
})

// 跳转修改密码
$(document).on("click", ".updatePassword", function () {
    window.location.replace("/updatePassword")
})

// 设备统计
function getEquipCount(ele, id) {
    $.ajax({
        url: "/equip/getCount?labId=" + id,
        type: "GET",
        success: function (result) {
            $(ele).text(result.data)
        }
    })
}

// 用户统计
function getUserCount(ele, id) {
    $.ajax({
        url: "/user/getCount?roleId=" + id,
        type: "GET",
        success: function (result) {
            $(ele).text(result.data)
        }
    })
}

// 实验室统计
function getLabCount(ele, id) {
    $.ajax({
        url: "/lab/getCount?managerId=" + id,
        type: "GET",
        success: function (result) {
            $(ele).text(result.data)
        }
    })
}

// 公告统计
function getNoticeCount(ele, id) {
    $.ajax({
        url: "/notice/getCount?publishId=" + id,
        type: "GET",
        success: function (result) {
            $(ele).text(result.data)
        }
    })
}

// 提示信息
function tipMsg(ele, status, msg) {
    if (status === "success") {
        ele.parent().removeClass("has-error").addClass("has-success")
        ele.next("span").text(msg)
    } else if (status === "error") {
        ele.parent().removeClass("has-success").addClass("has-error")
        ele.next("span").text(msg)
    }
}

// 全选操作
$(document).on("click", "#checkAll", function () {
    $(".checkItem").prop("checked", $(this).prop("checked"))
})
$(document).on("click", ".checkItem", function () {
    const checkAllFlag = $(".checkItem:checked").length === $(".checkItem").length
    $("#checkAll").prop("checked", checkAllFlag)
})

// 重置表单
function resetForm(ele) {
    $(ele)[0].reset()
    $(ele).find("*").removeClass("has-error has-success has-feedback has-warning")
    $(ele).find(".help-block").text("")
}

// 解析分页数据
function buildPageInfo(result) {
    totalPageNum = result.data.pages;
    totalPageSize = result.data.total;
    currentPageNum = result.data.current;
    currentPageSize = result.data.size;
    currentPageRecordNum = result.data.records.length;

    // 清空数据
    $("#pageInfoArea").empty()
    $("#pageNavArea").empty()
    // 分页信息
    $("#pageInfoArea").append("当前为第" + currentPageNum + "页，共" + totalPageNum + "页，当前页有" + currentPageRecordNum + "条记录，共" + totalPageSize + "条记录")
    // 分页条
    const ul = $('<ul></ul>').addClass("pagination")
    const firstPageLi = $('<li></li>').append($('<a></a>').append("首页"))
    const prePageLi = $('<li></li>').append($('<a></a>').append("&laquo;"))
    if (currentPageNum === 1) {
        firstPageLi.addClass("disabled")
        prePageLi.addClass("disabled")
    } else {
        firstPageLi.click(function () {
            toPage(1, 10)
        })
        prePageLi.click(function () {
            toPage(currentPageNum - 1, 10)
        })
    }
    const nextPageLi = $('<li></li>').append($('<a></a>').append("&raquo;"))
    const endPageLi = $('<li></li>').append($('<a></a>').append("末页"))
    if (currentPageNum === totalPageNum) {
        nextPageLi.addClass("disabled")
        endPageLi.addClass("disabled")
    } else {
        nextPageLi.click(function () {
            toPage(currentPageNum + 1, 10)
        })
        endPageLi.click(function () {
            toPage(totalPageNum, 10)
        })
    }

    ul.append(firstPageLi).append(prePageLi)
    for (let i = 1; i <= totalPageNum; i++) {
        const numLi = $('<li></li>').append($('<a></a>').append(i))
        if (i === currentPageNum) {
            numLi.addClass("active")
        }
        numLi.click(function () {
            toPage(i, 10)
        })
        ul.append(numLi)
    }

    ul.append(nextPageLi).append(endPageLi)

    const navEle = $('<nav></nav>').append(ul)
    navEle.appendTo("#pageNavArea")
}