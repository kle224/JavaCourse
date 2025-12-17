Eclipse IDE Setup

1. Shortcuts: Window > Preferences > General > Keys
	Run = F1
	Debug = CTRL+F1

2. Font Size : Window > Preferences > General > Apperance >  Colors and Fonts > Java > Java Editor Text Font > Edit

3. Templates: Window > Preferences > Java > Editor > Templates
	=> Befehl für das Pattern suchen oder anlegen
	so = System.out.println("");
	sp = System.out.print("");
	sof = System.out.println(string.format());

4. Auto Completion: Window > Preferences > Java > Editor >  Content Assist
	a) Enable Auto acitvation
	b) Set delay to 100ms
	c) paste in field "Auto activation triggers for Java:": ._abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ




Ablauf Einrichtung des Arbeitsplatzes:

1. Aufbau des Projektes in Eclipse
	Projekt = JavaCourse (allumfassend für den gesamten Kurs)
	Package = Section01 etc. (ein Package für jede Section)
	Class = Name des Programmierbeispiels und pro Package eine MainSection, welche das Kapitel startet

2. Erstellen des Projektes
	Windows-Zugriff: 	"C:\Users\noahk\Projekte\JavaCourse"
	WSL-Zugriff:		"/mnt/c/Users/noahk/Projekte/JavaCourse"

3. Git in WSL installieren
	sudo apt update
	sudo apt upgrade
	sudo apt install git
	git --Version

	git config --global user.name Noah
	git config --global user.email noahklein432+github@gmail.com

	cd /mnt/c/dev/java-kurs
	cat > .gitignore << 'EOF'
	/bin/
	/.settings/
	/.metadata/
	*.class
	*.log
	.DS_Store
	EOF
	
	git init
	git add .
	git commit -m "Initial Commit: Eclipse Java Project"

	ssh-keygen -t ed25519 -C "noahklein432+github@gmail.com"
	eval "$(ssh-agent -s)"
	ssh-add ~/.ssh/id_ed25519
	cat ~/.ssh/id_ed25519.pub

	git remote -v
	git remote set-url origin git@github.com:kle224/JavaCourse.git
	ssh -T git@github.com						// Testet Verbindung

	git remote add origin git@github.com:kle224/JavaCourse.git
	git push -u origin master


4. Git Stuff
	Branch anlegen: git branch -m <branch name>
	Branch umbenennen: git branch -m <old name> <new name>
	Branch löschen: git push origin --delete <branch name>

	Branch wechseln: git checkout <new branch>

	Merge: git merge <branch name>
		=> merges from the <branch name>-branch into your current branch
		   => merge = combining two development states without losses

	Pull: git pull origin <branch name>
	Push: git push origin <branch name>

	Checking current branch: git branch || git status

NICE TO KNOW:
notebook wsl: kle | G!1 | SSH-Phrase benteler
tower wsl:    noahk | G!1 | SSH-Phrase G!1
