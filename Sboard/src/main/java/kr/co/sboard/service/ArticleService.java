package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor //자동 주입
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;
    //entity를 dto로 dto를 entity로 자동 변환 시켜줌
    private final ModelMapper modelMapper;

    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getpPageable("no");
        //Pageable pageable = PageRequest.of(pg-1, 10, Sort.Direction.DESC, "no");

        Page<ArticleEntity> result = articleRepository.findByParentAndCate(0, pageRequestDTO.getCate(), pageable);
        //Page 클래스는 Spring Framework에서 데이터를 페이지별로 검색 및 반환하기 위한 페이징 및 정렬 정보를 포함하는 클래스
        //getContent(): 현재 페이지에 포함된 데이터를 반환하는 메서드입니다.
        //이 메서드는 현재 페이지의 엔티티나 DTO 객체들을 가져올 수 있습니다.

                                //map은 list 갯수만큼 동일한 처리(for문과 비슷)
                        //10개
       List<ArticleDTO> dtoList = result.getContent()
                                        .stream()
                                        .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                                        .toList();
        //modelMapper.map(entity, ArticleDTO.class)를 통해 ArticleDTO 객체로 변환.

       log.info("dtoList : " + dtoList);

        int totalElement = (int)result.getTotalElements();  //전체 게시글 갯수


        return PageResponseDTO.builder()
                              .pageRequestDTO(pageRequestDTO)
                              .dtoList(dtoList)
                              .total(totalElement)
                              .build();
    }

    public void save(ArticleDTO dto){

        // 글 등록
        ArticleEntity savedEntity = articleRepository.save(dto.toEntity());

        // 파일 업로드
        FileDTO fileDTO = fileUpload(dto);

        if(fileDTO != null){
            // 파일 등록
            fileDTO.setAno(savedEntity.getNo());
            fileRepository.save(fileDTO.toEntity());
        }
    }


    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    public FileDTO fileUpload(ArticleDTO dto) {

        log.info("fileUpload...1");
        MultipartFile mf = dto.getFname();

        log.info("fileUpload...2 : " + mf);

        if(!mf.isEmpty()){
            // 파일 첨부 했을 경우
            String path = new File(filePath).getAbsolutePath();
            log.info("fileUpload...3 : " + path);

            String oName = mf.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String sName = UUID.randomUUID().toString()+ext;

            log.info("fileUpload...4 : " + oName);

            try {
                log.info("fileUpload...5");
                mf.transferTo(new File(path, sName));
                log.info("fileUpload...6");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            log.info("fileUpload...7");
            return FileDTO.builder()
                    .ofile(oName)
                    .sfile(sName)
                    .build();
        }
        log.info("fileUpload...8");
        return null;
    }

}
