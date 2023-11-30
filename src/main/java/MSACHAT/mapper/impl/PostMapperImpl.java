package MSACHAT.mapper.impl;
import MSACHAT.dto.PostDto;
import MSACHAT.entities.Post;
import MSACHAT.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
public class PostMapperImpl implements Mapper<Post, PostDto> {


    private ModelMapper modelMapper;
    public PostMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto mapTo(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public Post mapFrom(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }
}
