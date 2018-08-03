package com.nebula.demo.service;

import com.nebula.demo.entity.Block;
import com.nebula.demo.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Cacheable(cacheNames = "blocks")
public class BlockChainService {

    @Autowired
    BlockRepository blockRepository;

    public Page<Block> getLatestBlocks(Pageable pageable){

        return blockRepository.getLatestBlocks(pageable);
    }

}
