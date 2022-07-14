package cnovaez.dev.notebookmvvm.domain.model


import cnovaez.dev.notebookmvvm.utils.PriorityTypes

data class Note(
    val title: String,
    val description: String,
    val icon: Int,
    val date: String,
    val priority: PriorityTypes,
    val image_data: Array<Array<Byte>>,
    val voice_data: Array<Array<Byte>>,
    val text_data: Array<Array<String>>
)
