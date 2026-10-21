package com.jobtrack;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.jobtrack.entity.Job;
import com.jobtrack.repository.JobRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
@Controller
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @GetMapping("/")
    public String home(){
        return "forward:/index.html";
    }
    @PutMapping("/jobs/{id}")
@ResponseBody
public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {

    Job job = jobRepository.findById(id).orElseThrow();

    job.setCompanyName(updatedJob.getCompanyName());
    job.setPosition(updatedJob.getPosition());
    job.setStatus(updatedJob.getStatus());

    return jobRepository.save(job);
}
    @DeleteMapping("/jobs/{id}")
    @ResponseBody
    public void deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
    }

    @GetMapping("/jobs")
    @ResponseBody
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @PostMapping("/jobs")
    @ResponseBody
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }
}