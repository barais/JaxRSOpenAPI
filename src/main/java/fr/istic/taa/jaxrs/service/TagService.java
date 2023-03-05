package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.impl.TagDao;
import fr.istic.taa.jaxrs.domain.Tag;

import java.util.List;

public class TagService {
    private final TagDao tagDao;

    public TagService() {
        this.tagDao=new TagDao();
    }
    public Tag getTag(Long id){
        return tagDao.findOne(id);
    }
    public List<Tag> getTags(){
        return tagDao.findAll();
    }
    public void addTag(Tag tag){
        tagDao.save(tag);
    }
    public void removeTag(Long id){
        tagDao.deleteById(id);
    }

    public void updateTag(Long id, Tag tag){
        if(tagDao.findOne(id)==null){
            throw new RuntimeException("Aucun Tag trouvé");
        }
        else{
            Tag update = tagDao.findOne(id);
            update.setLibelle(tag.getLibelle());
            tagDao.update(update);
        }
    }
}
