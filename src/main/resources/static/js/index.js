const moreBtn = document.querySelector(".member-list-btn");

const memberListGroup = document.querySelector(".list-group");

let members;

const INIT_PROFILE_NUM = 6;	

init();

function init(){
  /* 프로필 로딩 */
  loadProfiles(INIT_PROFILE_NUM);
}

moreBtn.addEventListener("click", () => {
  let LOAD_PROFILE_NUM;

  if(moreBtn.innerText !== "더보기"){
	LOAD_PROFILE_NUM=INIT_PROFILE_NUM;
	moreBtn.innerText="더보기";
  }else{
	LOAD_PROFILE_NUM="all";
	moreBtn.innerText="접기";
  }

  memberListGroup.innerHTML = "";
  loadProfiles(LOAD_PROFILE_NUM);
});

async function loadProfiles(num) {
	const REQ_URL = `http://localhost:8080/api/members?num=${num}`;
	let members;
	
    const res = await fetch(REQ_URL)
				    .then((res) => {
				      return res.json();
				    })
				    .then((data) => {
				      members = data;
				    });

	members.forEach(m=> createProfile(m));
}

function createProfile(member) {
  // 프로필 생성
  const memberProfile = document.createElement("li");
  memberProfile.classList.add("club-members-profile"); // 클래스명 추가

  // 프로필 헤더 생성
  const memberProfileHead = document.createElement("div");
  const memberProfileImage = document.createElement("img");

  memberProfileHead.classList.add("member-profile-head");
  memberProfileImage.classList.add("rounded-circle");

  memberProfileImage.src = `data:image/png;base64, ${member.profileImage}`;
  memberProfileImage.alt = "...";
  memberProfileHead.appendChild(memberProfileImage);

  // 프로필 바디 생성
  const memberProfileBody = document.createElement("div");
  const memberProfileId = document.createElement("div");
  const memberProfileName = document.createElement("div");
  const memberProfileRole = document.createElement("div");
  const memberProfileIntro = document.createElement("div");

  memberProfileBody.classList.add("member-profile-body");
  memberProfileId.classList.add("profile-id");
  memberProfileName.classList.add("profile-name");
  memberProfileRole.classList.add("profile-role");
  memberProfileIntro.classList.add("profile-interest");

  memberProfileId.style.display = "none";
  memberProfileName.innerText = `${member.profileName}`;
  memberProfileRole.innerText = `${member.role}`;
  memberProfileIntro.innerText = `${member.profileIntro}`;

  memberProfileBody.appendChild(memberProfileId);
  memberProfileBody.appendChild(memberProfileName);
  memberProfileBody.appendChild(memberProfileRole);
  memberProfileBody.appendChild(memberProfileIntro);

  memberProfile.appendChild(memberProfileHead);
  memberProfile.appendChild(memberProfileBody);
  memberListGroup.appendChild(memberProfile);
}
