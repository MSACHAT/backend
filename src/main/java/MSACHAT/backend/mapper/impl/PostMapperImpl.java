package MSACHAT.backend.mapper.impl;
import MSACHAT.backend.mapper.Mapper;
import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.entity.PostEntity;
import org.modelmapper.ModelMapper;

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
