package lpnu.service.impl;

import lpnu.dto.BookDTO;
import lpnu.exception.ServiceException;
import lpnu.mapper.BookToBookDTOMapper;
import lpnu.repository.BookRepository;
import lpnu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookToBookDTOMapper bookMapper;

    @Override
    public BookDTO getBookById(final Long id) {
        return bookMapper.toDTO(bookRepository.getBookById(id));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.getAllBooks().stream()
                .map(e -> bookMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO createBook(final BookDTO bookDTO) {
        if (bookDTO.getId() != null) {
            throw new ServiceException(400, "Book should not have an id", null);
        }

        return bookMapper.toDTO(bookRepository.createBook(bookMapper.toEntity(bookDTO)));
    }

    @Override
    public BookDTO updateBook(final BookDTO bookDTO) {
        if (bookDTO.getId() == null) {
            throw new ServiceException(400, "Book should have an id", null);
        }

        return bookMapper.toDTO(bookRepository.updateBook(bookMapper.toEntity(bookDTO)));
    }

    @Override
    public void deleteBookById(final Long id) {
        bookRepository.deleteBookById(id);
    }
}
