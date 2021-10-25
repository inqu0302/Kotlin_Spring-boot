$(function(){
    const button = $("section.detail button")
    if(button) {
        $(button).on("click",function(){
            const className = $(this).attr("class")
            let href = `${rootPath}buyer`
            if(className.includes("btn_update")) {
                 alert(`Update ${userid}`)
                href = `${href}/update/${userid}`
            } else if(className.includes("btn_delete")) {
                 alert(`delete ${userid}` )
                href = `${href}/delete/${userid}`
                if( !confirm("삭제할까요?") ) {
                    return false
                }
            } else if(className.includes("btn_list")){
                alert(`list`)
                href = `${rootPath}`
            }
            location.href = `${href}`
        })
    }
})