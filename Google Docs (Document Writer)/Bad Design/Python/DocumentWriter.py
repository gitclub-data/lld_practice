
class DocumentWriter:
    def __init__(self):
        self._elements = []

    # methods
    def addtext(self, text):
        self._elements.append(text)

    def addimage(self, image):
        self._elements.append("[Image: " + image + " ]")

    def changetexttobold(self, startindex, endindex):
        for i in range(startindex, min(len(self._elements), endindex + 1)):
            element = self._elements[i]
            element = "**" + element + "**"
            self._elements[i] = element
    
    def changetexttoitellic(self, startindex, endindex):
        for i in range(startindex, min(len(self._elements), endindex + 1)):
            element = self._elements[i]
            element = "*" + element + "*"
            self._elements[i] = element
    
    def renderer(self):
        content = ""
        for element in self._elements:
            content += str(element) + "\n"
        return content
    
    def save(self):
        content = self.renderer()
        with open("document.txt", "w") as file:
            file.write(content)
        print("Document saved as document.txt")


if __name__=="__main__":    
    print("This module is not intended to be run directly.")
    doc = DocumentWriter()
    text1 = "Hello, this is a sample document. "
    text2 = "This document contains text and images. "
    image1 = "image1.png"
    doc.addtext(text1)
    doc.addtext(text2)
    doc.addimage(image1)
    doc.changetexttobold(0,1)
    doc.save()
