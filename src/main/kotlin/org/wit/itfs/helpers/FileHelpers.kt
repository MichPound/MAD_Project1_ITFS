package org.wit.itfs.helpers

import java.io.*

fun writeFile(fileName: String, data: String) {
    val file = File(fileName)

    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        println("File writing error: $e")
    }
}

fun readFile(fileName: String): String {
    val file = File(fileName)
    var data = ""

    try {
        val inputStreamReader = InputStreamReader(FileInputStream(file))

        if (inputStreamReader != null) {
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialString = StringBuilder()
            var complete = false

            while (!complete) {
                val line = bufferedReader.readLine()
                complete = (line == null)
                if (line != null) partialString.append(line)
            }
            inputStreamReader.close()
            data = partialString.toString()
        }
    } catch (e: FileNotFoundException) {
        println("File read error: $e")
    } catch (e: IOException) {
        println("File read error: $e")
    }
    return data
}

fun fileExists(fileName: String): Boolean {
    val file = File(fileName)
    return file.exists()
}
