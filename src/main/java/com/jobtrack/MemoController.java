package com.jobtrack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.entity.Memo;
import com.jobtrack.repository.MemoRepository;

@RestController
public class MemoController {

    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping("/memo")
    public Memo getMemo() {
        return memoRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(Memo::new);
    }

    @PostMapping("/memo")
    public Memo saveMemo(@RequestBody Memo memo) {

        Memo existingMemo = memoRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        if (existingMemo != null) {
            existingMemo.setSelfPr(memo.getSelfPr());
            existingMemo.setMotivation(memo.getMotivation());
            existingMemo.setMemo(memo.getMemo());

            return memoRepository.save(existingMemo);
        }

        return memoRepository.save(memo);
    }
}