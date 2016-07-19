#include<stdio.h>
#include<arpa/inet.h>
#include<string.h>
int main(){
	char str[100],str1[100];
	int clientsockfd,l;
	struct sockaddr_in serveraddress;
	serveraddress.sin_port=htons(3000);
	serveraddress.sin_addr.s_addr=inet_addr("127.0.0.1");
	serveraddress.sin_family=AF_INET;
	clientsockfd=socket( AF_INET, SOCK_STREAM, 0 );
	if(connect(clientsockfd,(struct sockaddr*)&serveraddress,sizeof(serveraddress))<0){
		printf("Can not Connect");
	}
	printf("Enter the String : ");
	scanf("%s",str);
	l=strlen(str);
	write(clientsockfd,str,l+1);
	read(clientsockfd,str1,l+1);
	printf("Reversed String is : %s\n",str1);
	close(clientsockfd);
	return 0;
}

