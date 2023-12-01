package MSACHAT.mapper.impl;
import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
public class PostMapperImpl implements Mapper<PostEntity, PostDto> {


    private ModelMapper modelMapper;
    public PostMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto mapTo(PostEntity post) {
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostEntity mapFrom(PostDto postDto) {
        return this.modelMapper.map(postDto, PostEntity.class);
    }
}
