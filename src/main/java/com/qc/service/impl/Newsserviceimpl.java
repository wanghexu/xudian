package com.qc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qc.service.Newsservice;

/**
 * 新闻业务层
 * @author whx
 *
 */
@Service
@Transactional(readOnly = false)
public class Newsserviceimpl implements Newsservice{

}
