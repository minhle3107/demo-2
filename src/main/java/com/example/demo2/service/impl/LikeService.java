package com.example.demo2.service.impl;

import com.example.demo2.dto.LikeDTO;
import com.example.demo2.entity.Like;
import com.example.demo2.mapper.LikeMapper;
import com.example.demo2.model.LikeModel;
import com.example.demo2.repository.ILikeRepository;
import com.example.demo2.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements ILikeService {

    @Autowired
    private ILikeRepository iLikeRepository;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public List<LikeDTO> getAll() {
        return List.of();
    }

    @Override
    public long create(LikeModel likeModel) {

        Like likeCreate = likeMapper.toEntity(likeModel);

        LikeDTO likeDTO = likeMapper.toDto(likeCreate);

        long employeeId = likeDTO.getEmployee_id();
        long postId = likeDTO.getPost_id();

        LikeDTO likeFind = getByEmployeeIdAndPostId(employeeId, postId);

        if (likeFind != null) {
            return 0;
        }

        iLikeRepository.create(likeCreate);


        return 1;
    }

    @Override
    public LikeDTO update(long id, LikeModel likeModel) {
        return null;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public LikeDTO getById(long id) {
        return null;
    }

    @Override
    public LikeDTO getByEmployeeIdAndPostId(long employeeId, long postId) {
        return likeMapper.toDto(iLikeRepository.getByEmployeeIdAndPostId(employeeId, postId));
    }

    @Override
    public long unLike(long employeeId, long postId) {

        LikeDTO likeFind = getByEmployeeIdAndPostId(employeeId, postId);

        if (likeFind == null) {
            return 0;
        }

        long idLike = likeFind.getId();

        iLikeRepository.delete(idLike);

        return 1;
    }
}
