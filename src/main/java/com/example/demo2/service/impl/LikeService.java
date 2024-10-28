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


        long employeeId = likeCreate.getEmployee_id();
        long postId = likeCreate.getPost_id();

        long likeFindId = getByEmployeeIdAndPostId(employeeId, postId);

        if (likeFindId != 0) {
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
    public long getByEmployeeIdAndPostId(long employeeId, long postId) {

        if (iLikeRepository.getByEmployeeIdAndPostId(employeeId, postId) == 0) {
            return 0L;
        }

        return iLikeRepository.getByEmployeeIdAndPostId(employeeId, postId);
    }

    @Override
    public long unLike(LikeModel likeModel) {

        long employeeId = likeModel.getEmployee_id();
        long postId = likeModel.getPost_id();

        long likeFindId = getByEmployeeIdAndPostId(employeeId, postId);

        if (likeFindId == 0) {
            return 0;
        }

        iLikeRepository.delete(likeFindId);

        return 1;
    }
}
