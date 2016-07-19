#include<stdio.h>
#include<arpa/inet.h>
#include<string.h>
void reverse(char *str,int l);
int main(){
	char s[100];
	int serversockfd,l,newserversockfd,clength;
	struct sockaddr_in clientaddress,serveraddress;
	serveraddress.sin_family=AF_INET;
	serveraddress.sin_addr.s_addr=inet_addr("127.0.0.1");
	serveraddress.sin_port=htons(3000);
	serversockfd=socket(AF_INET,SOCK_STREAM,0);
	bind(serversockfd,(struct sockaddr *)&serveraddress,sizeof(serveraddress));
	listen(serversockfd,5);	
	clength=sizeof(clientaddress);
	newserversockfd=accept(serversockfd,(struct sockaddr*)&clientaddress,&clength);
	while(1){
		read(newserversockfd,s,100);
		printf("Recieved : %s\n",s);
		l=strlen(s);
		reverse(s,l);
		write(newserversockfd,s,l+1);
	}
	close(newserversockfd);
	close(serversockfd);
	return 0;
}

void reverse(char *str,int l){
	int i;
	char c;
	for(i=0;i<l/2;i++){
		c=str[i];
		str[i]=str[l-i-1];
		str[l-i-1]=c;
	}
}