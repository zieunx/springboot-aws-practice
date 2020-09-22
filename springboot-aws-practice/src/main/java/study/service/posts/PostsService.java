package study.service.posts;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.posts.PostsRepository;
import study.web.dto.PostSaveRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
