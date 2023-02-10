package com.broadcast.myapplication.adapter.fingerprints

//
//class PostFingerprint(
//    private val onSavePost: (ArticlesPresentation) -> Unit,
//    private val itemWidth: Int? = null
//) : ItemFingerprint<ItemApiBinding, ArticlesPresentation> {
//
//    override fun isRelativeItem(item: Item) = item is ArticlesPresentation
//
//    override fun getLayoutId() = R.layout.item_api
//
//    override fun getViewHolder(
//        layoutInflater: LayoutInflater,
//        parent: ViewGroup
//    ): BaseViewHolder<ItemApiBinding, ArticlesPresentation> {
//        val binding = ItemApiBinding.inflate(layoutInflater, parent, false)
//        binding.root.layoutParams = binding.root.layoutParams.also {
//            it.width = itemWidth ?: it.width
//        }
//        return PostViewHolder(binding, onSavePost)
//    }
//
//    override fun getDiffUtil() = diffUtil
//
//    private val diffUtil = object : DiffUtil.ItemCallback<UserPost>() {
//
//        override fun areItemsTheSame(oldItem: ArticlesPresentation, newItem: ArticlesPresentation) = oldItem. == newItem.postId
//
//        override fun areContentsTheSame(oldItem: ArticlesPresentation, newItem: ArticlesPresentation) = oldItem == newItem
//
//        override fun getChangePayload(oldItem: UserPost, newItem: UserPost): Any? {
//            if (oldItem.isSaved != newItem.isSaved) return newItem.isSaved
//            return super.getChangePayload(oldItem, newItem)
//        }
//
//    }
//
//}
//
//class PostViewHolder(
//    binding: ItemApiBinding,
//    val onSavePost: (UserPost) -> Unit
//) : BaseViewHolder<ItemPostBinding, UserPost>(binding) {
//
//    init {
//        binding.tbLike.setOnClickListener {
//            if (bindingAdapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
//            onSavePost(item)
//        }
//    }
//
//    override fun onBind(item: UserPost) {
//        super.onBind(item)
//        with(binding) {
//            tvCommentCount.text = item.commentsCount
//            tvLikesCount.text = item.likesCount
//            tvTitle.text = item.mainComment
//            ivPostImage.setImageDrawable(item.image)
//            tbLike.setChecked(item.isSaved)
//        }
//    }
//
//    override fun onBind(item: UserPost, payloads: List<Any>) {
//        super.onBind(item, payloads)
//        val isSaved = payloads.last() as Boolean
//        binding.tbLike.setChecked(isSaved)
//    }
//
//    private fun ImageView.setChecked(isChecked: Boolean) {
//        val icon = when (isChecked) {
//            true -> R.drawable.ic_bookmark_fill_24
//            false -> R.drawable.ic_bookmark_border_24
//        }
//        setImageResource(icon)
//    }
//
//}
