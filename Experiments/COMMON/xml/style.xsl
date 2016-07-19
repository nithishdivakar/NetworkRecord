<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
<body style="background-color: white">
<div style="width: 500px;height: 540px;margin: auto;border:3px solid rgb(209, 209, 209);">
	<div style="width:500px;height:50px;background-color: rgb(209, 209, 209);text-align:center;">
		<span style="font-size:20px;line-height:50px">
			<xsl:value-of select="syllabus/subject"/>
		</span>
	</div>
	<div style="width:500px;height:30px;background-color: white;">
		<span style="font-size:20px;line-height:30px">
			Subject Code: <xsl:value-of select="syllabus/code"/>
		</span>
	</div>
	<xsl:for-each select="syllabus/module">
		<div  style="width: 500px;height: 150px;">
			<div style="width:500px;height:20px;background-color: rgb(209, 209, 209);">
				<span style="font-size:18px;line-height:20px">
					Module Name:<xsl:value-of select="name"/>
				</span>
			</div>		
			<xsl:for-each select="topic">
				<div style="width:500px;height:20px;">
					<span style="font-size:18px;line-height:20px">	
						<xsl:value-of select="."/>
					</span>
				</div>	
			</xsl:for-each>	
		</div>
	</xsl:for-each>
</div>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
