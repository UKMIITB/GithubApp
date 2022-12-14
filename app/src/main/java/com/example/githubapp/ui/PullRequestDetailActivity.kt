package com.example.githubapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubapp.databinding.ActivityPullRequestDetailBinding
import com.example.githubapp.helper.Constants.Companion.PULLREQUESTDATA
import com.example.githubapp.helper.PullRequestDetailVHUtil
import com.example.githubapp.model.PullRequest

class PullRequestDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestDetailBinding
    private var pullRequest: PullRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pullRequest = intent.getParcelableExtra(PULLREQUESTDATA)

        setupView()
    }

    private fun setupView() {
        pullRequest?.let {
            binding.pullRequestTitleTv.text = it.title
            binding.closedDateTv.text =
                PullRequestDetailVHUtil.getClosedDateDisplayText(it.closed_at)
            binding.createdDateTv.text =
                PullRequestDetailVHUtil.getCreatedDateDisplayText(it.created_at)
            binding.userNameTv.text = PullRequestDetailVHUtil.getUserNameDisplayText(it.user.name)
            Glide.with(binding.userProfileIv.context)
                .load(it.user.avatar_url)
                .into(binding.userProfileIv)
        }
    }
}