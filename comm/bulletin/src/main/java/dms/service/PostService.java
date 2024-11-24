package dms.service;

import dms.domain.Member;
import dms.domain.Post;
import dms.dto.PostRequestDto;
import dms.dto.PostResponseDto;
import dms.MemberRepository;
import dms.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }


    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Member member = memberRepository.findById(postRequestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setMember(member);

        Post savedPost = postRepository.save(post);
        return toResponseDto(savedPost);
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
    private PostResponseDto toResponseDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthorName(post.getMember().getMemberName());
        return dto;
    }
}
