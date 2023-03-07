package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.TagDao;
import fr.istic.taa.jaxrs.dao.impl.TagDao;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.dto.TagDto;

import java.util.ArrayList;
import java.util.List;

public class TagService {
    TagDao tagDao = new TagDao();

    public TagService() {
    }
    public TagDto getTag(Long id){
        Tag tag = tagDao.findOne(id);
        return new TagDto(tag.getLibelle(),tag.getTickets());
    }
    public List<TagDto> getTags(){
        List<TagDto> tags = new ArrayList<>();
        for(Tag tag:tagDao.findAll()){
            TagDto tagDto = new TagDto(tag.getLibelle(),tag.getTickets());
            tags.add(tagDto);
        }
        return tags;
    }
    public void addTag(Tag tag){
        tagDao.save(tag);
    }
    public void removeTag(Long id){
        tagDao.deleteById(id);
    }

    public void updateTag(Long id, Tag tag){
        if(tagDao.findOne(id)==null){
            throw new RuntimeException("Aucun tag trouvé");
        }
        else{
            Tag update = tagDao.findOne(id);
            update.setLibelle(tag.getLibelle());
            tagDao.update(update);
        }
    }
}
