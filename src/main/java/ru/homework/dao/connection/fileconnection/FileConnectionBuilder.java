package ru.homework.dao.connection.fileconnection;

import java.nio.file.Path;

/**
 * В качестве источника данных могут использоваться различные типы файлов
 * Этот интерфейс служит для того, чтобы при его использовании
 * можно было получать доступ к источнику данных в виде файла, без привязки к конкретному типу
 */
public interface FileConnectionBuilder {

    Path getFile();
}
