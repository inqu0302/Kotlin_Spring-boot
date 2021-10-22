
$(()=>{

    function tableClickHandler() {
        const id = $(this).data("id")
        location.href = `${rootPath}/order/detail/${id}`
     }
    $("table.order_list tr").on("click", tableClickHandler)

})
