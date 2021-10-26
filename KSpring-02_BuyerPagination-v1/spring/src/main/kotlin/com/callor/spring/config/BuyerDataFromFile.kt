package com.callor.spring.config

import com.callor.spring.ConfigData
import com.callor.spring.models.Buyer
import com.callor.spring.repository.BuyerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.function.Consumer
import javax.transaction.Transactional

// 프로젝트가 시작하면 자동으로 실행되는 클래스
@SpringBootConfiguration
class BuyerDataFromFile(val buyerDao: BuyerRepository) {

    /**
     * @Value()
     * *.properties 또는 *.yml 파일에 설정된 속성을 참조하여
     * 속성(com.callor.spring.student-file)에 설정된 값(주로 문자열)을 가져와서
     * dataFile 변수에 담아라
     */
    @Value("\${com.callor.spring.student-file}")
    private val dataFile: String? = null

//    00126:매재찬:경제학:2:1:울산광역시 울주군 문수로382:010-6239-1705
    private val 학번 = 0
    private val 이름 = 1
    private val 학과 = 2
    private val 학년 = 3
    private val 반 = 4
    private val 주소 = 5
    private val 전화번호 = 6



    @Bean
    @Transactional
    fun dataFromFile():CommandLineRunner{

        logger().debug("Data File: {}", dataFile)

        // dataFile에 null 값을 허용했기때문에 아래처럼 사용
        // dataFile에 담긴 값을 file 에 담는데
        // ClassPathResource으로 전달(let, 할당)해서 dataFile 을 가져온다
        // ClassPathResource() 함수의 매개변수로 받아 return 값을 file 에 담는다
        // val file = ClassPathResource("student.txt")
        // val file = ClassPathResource(dataFile) <- null 값 허용이 되어있기때문에 문제가 발생할수 있다
        val file = dataFile?.let { ClassPathResource(it) }

        // ClassPathResource() 함수를 사용하여
        // Resources 폴더에 저장된 파일 정보를 가져오면
        // 다음과 같은 속성값을 취할 수 있다
        logger().debug("파일 객체 {}",file?.file.toString()) // getFile()
        logger().debug("파일 이름 {}",file?.filename.toString()) // getFileName()
        logger().debug("InputStream {}",file?.inputStream.toString()) // getInputStream()
        logger().debug("파일 저장 경로 {}",file?.path.toString()) // getPath()
        logger().debug("URL {}",file?.url.toString()) // getURL()
        logger().debug("URI {}",file?.uri.toString()) // getURI()

        /**
         * java 1.8 이상에서 사용하는 text file 읽기 코드
         * 파일의 uri 정보를 Paths 객체에 주입하여 Path 객체를 만든다
         * Files 클래스의 readAllLines() 함수에 Path 객체를 주입하면
         * text 파일의 전체 내용을 한 라인씩 잘라서 List 데이터로 생성해 준다
         */
        // file에 값이 있으면 uri 를 가지고 path를 생성
        val path: Path = Paths.get(file?.uri )

        val fileList: List<String> = Files.readAllLines(path)

        // fileList 크기만큼 반복하면서 list 에 담긴 데이터를 출력
        fileList.forEach( Consumer { str: String -> logger().debug(str)})

        val managerList = fileList.map{
            val lines = it.split(":")

            // {이름 : "홍길동", 전화번호 : "010-1234-5678"} 형식의 데이터 생성
            mapOf(이름 to lines[이름], 전화번호 to lines[전화번호])
        }

        val buyerList = fileList.mapIndexed { index, str ->

            // : 를 기준으로 문자열을 분해하고 map 형식으로 데이터를 저장
            val lines = str.split(":")
            val userid = String.format("B%03d", index+1)

            val mSize = managerList.size
            val mIndex = ConfigData.RND.nextInt(mSize)
            val manager = managerList[mIndex]
            
            Buyer(
                userid = userid,
                name = lines[이름],
                tel = lines[전화번호],
                address = lines[주소],
                manager = manager[이름],
                man_tel = manager[전화번호]
            )
        }

        buyerDao.saveAll(buyerList)

        return CommandLineRunner {
            logger().debug(it.toString())
        }
    }
}