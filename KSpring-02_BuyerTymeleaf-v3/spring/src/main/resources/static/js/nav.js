$(function(){

    $("nav li").on("click", function(){

        // this : js 에서 e.currentTarget 과 같다
        const className = $(this).attr("class")
        const tagId = $(this).attr("id")
        const tagName = $(this).attr("name")
        const text = $(this).text()

        /*
        JS Debuging
        여러개 변수 데이터를 alert()으로 보고싶을때 데이터들을 JSON 객체로 만들고
        JSON.stringify() 문자열로 바꿔서 alert() 에 표시
        */
        const tagInfo ={
            tagName, tagId, className, text
        }
        alert(JSON.stringify(tagInfo))

        let href = "/"
        if(text === "주문관리"){
            href += "order/list"
        } else if (text === "결제관리"){
            href += "pay"
        } else if (text === "로그인"){
            href += "login"
        }

        location.href = `${href}`

    })
})