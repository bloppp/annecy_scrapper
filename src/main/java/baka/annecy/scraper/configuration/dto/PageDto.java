package baka.annecy.scraper.configuration.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class PageDto<T> {

  private Integer page;

  private Integer size;

  private List<T> content;

  private Integer pageCount;

  private Long itemCount;

  public PageDto(Page<T> page) {
    this.size = page.getSize();
    this.page = page.getNumber();
    this.content = page.getContent();
    this.pageCount = page.getTotalPages();
    this.itemCount = page.getTotalElements();
  }
}
