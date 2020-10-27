package study.service.posts;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.posts.Posts;
import study.domain.posts.PostsRepository;
import study.web.dto.PostSaveRequestDto;
import study.web.dto.PostsResponseDto;
import study.web.dto.PostsUpdateRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        /*

        update 기능에서 데이터베이스에 쿼리를 날리지 않는다.
        JPA의 영속성 컨텍스트 때문에 가능하다.

        영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다.
        (일종의 논리적 개념)
        JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.

        JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면
        이 데이터는 영속성 컨텍스트가 유지된 상태이다.
        이 상태에서 해당 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.

        즉, 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다.

        이 개념을 "더티 체킹"이라고 한다.

        *  */
        posts.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);

    }
}
