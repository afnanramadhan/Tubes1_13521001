all:
	javac -cp ./src --module-path "./javafx-sdk-linux/lib" --add-modules=javafx.controls,javafx.fxml src/*.java
	java -cp ./src --module-path "./javafx-sdk-linux/lib" --add-modules=javafx.controls,javafx.fxml Main
build:
	javac -cp ./src --module-path "./javafx-sdk-linux/lib" --add-modules=javafx.controls,javafx.fxml src/*.java
run:
	java -cp ./src --module-path "./javafx-sdk-linux/lib" --add-modules=javafx.controls,javafx.fxml Main