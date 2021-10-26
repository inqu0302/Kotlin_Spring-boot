
$(()=>{

    function tableClickHandler() {
        const id = $(this).data("id")
        location.href = `${rootPath}/order/detail/${id}`
     }

    $(document).on("click", "table.order_list tr", tableClickHandler)

})
