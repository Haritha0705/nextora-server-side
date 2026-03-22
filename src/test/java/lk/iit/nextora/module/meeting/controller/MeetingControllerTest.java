package lk.iit.nextora.module.meeting.controller;
import lk.iit.nextora.common.dto.ApiResponse;
import lk.iit.nextora.common.dto.PagedResponse;
import lk.iit.nextora.common.enums.MeetingStatus;
import lk.iit.nextora.module.meeting.dto.request.*;
import lk.iit.nextora.module.meeting.dto.response.MeetingResponse;
import lk.iit.nextora.module.meeting.service.MeetingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("MeetingController Unit Tests")
class MeetingControllerTest {
    @Mock private MeetingService meetingService;
    @InjectMocks private MeetingController controller;
    @Nested
    @DisplayName("createMeetingRequest")
    class CreateMeetingRequestTests {
        @Test
        @DisplayName("Should submit meeting request")
        void createMeetingRequest_success() {
            CreateMeetingRequest request = CreateMeetingRequest.builder()
  import org.junit.jupiter.api.Test;
ubimport org.junit.jupiter.api       import org.mockito.InjectMocks;
import org.mockituiimport org.mockito.Mock;
impor(1import org.mockito.juni.Pimport org.springframework.data.domain.PageRequescrimport org.springframework.data.domain.Pageable;
i  import java.time.LocalDateTime;
import java.uticoimport java.util.List;
import reimport s            assimport static org.mockito.ArgumentMatchers.*;
import staltimport static org.mockito.Mockito.*           @ExtendWith(MockitoExtension.class)ng@DisplayName("Me        }
    }
    @Nested
    @DisplayName("getMyMeetingRequest        class GetMyMeetingRequ    @InjectMocks private M        @DisplayName("S    @Nested
    @DisplayName("createMeetingRequest")
es    @Displ()    class CreateMeetingRequestTests {
 =         @Test
        @DisplayName("          @Dis1L        void createMeetingRequest_success            PagedResponse<MeetingResponse> response = Paged  import org.junit.jupiter.api.Test;
ubimport org.junit.jupiter.api     mbubimport org.junit.jupiter.api       import org.mockituiimport org.mockito.Mock;
impor(1import org.mock   impor(1import org.mockito.juni.Pimport org  i  import java.time.LocalDateTime;
import java.uticoimport java.util.List;
import reimport s            assimport static o          import java.uticoimport java.util
 import re    }
    @Nested
    @DisplayNimport staltimport static or    class AcceptMeetingRequestTests {
        @Tes    }
    @Nested
    @DisplayName("getMyMeetingRequest        class GetMyMeetingRequ    @InjectMocks private M        Re    t     @DisplAc    @DisplayName("createMeetingRequest")
es    @Displ()    class CreateMeetingRequestTests {
 =         @Test
        @Displayates    @Displ()    class C            Meet =         @Test
        @DisplayName("                     @Displa  ubimport org.junit.jupiter.api     mbubimport org.junit.jupiter.api       import org.mockituiimport org.mockito.Mock;
impor(1import org.mock   impor(1import org.mockito.juniApimpor(1import org.mock   impor(1import org.mockito.juni.Pimport org  i  import java.time.LocalDateTime;
import java.ssimport java.uticoimport java.util.List;
import reimport s            assimport stati    }
    @Nested
    @DisplayName("rejectMeetingRequest")
  import re    }
    @Nested
    @DisplayNimport sta        @DisplayName("Should reject meet    @N        vo    @Displee        @Tes    }
    @Nested
    @DisplayName("getMyMeetingRequest        clin    @Nested
    ()            es    @Displ()    class CreateMeetingRequestTests {
 =         @Test
        @Displayates    @Displ()    class C            Meet =         @Test
        @Displa.R =         @Test
        @Displayates    @Displ() re        @Displaes        @DisplayName("                     @Displa  ubimport             Apiimpor(1import org.mock   impor(1import org.mockito.juniApimpor(1import org.mock   impor(1import org.mockito.juni.Pimport org  i  import java.time.LocalDateTime;
import tiimport java.ssimport java.uticoimport java.util.List;
import reimport s            assimport stati    }
    @Nested
    @DisplayName("rejectMeetingRequest")
  ouimport reimport s            assimport stati    }
  ng    @Nested
    @DisplayName("rejectMeetingRequet     @DisplRe  import re    }
    @Nested
                @Nested
   wS    @Displar    @Nested
    @DisplayName("getMyMeetingRequest        clin    @Nested
    ()            es    @Displ()  et    @Disple     ()            es    @Displ()    c                    .id( =         @Test
        @Displayates    @Displ()    class C        gS        @Displale        @Displa.R =         @Test
        @Displayates    @Displ()                  @Displayates    @Displ()reimport tiimport java.ssimport java.uticoimport java.util.List;
import reimport s            assimport stati    }
    @Nested
    @DisplayName("rejectMeetingRequest")
  ouimport reimport s            assimport stati    }
  ng    @Nested
    @DisplayName("rejectMeetingRequet     @        @Timport reimport s            assimport stati    }
    @Nes         @Nested
    @DisplayName("rejectMeetingRequeee    @Displse  ouimport reimport s            assim     ng    @Nested
    @DisplayName("rejectMeetingRequeLE    @Display        @Nested
                @Nested
   wS    @Displar    @Nested
se               wS    @Displar    @Re    @DisplayName("getMyMeet.g    ()            es    @Displ()  et    @Disple     ()     ge        @Displayates    @Displ()    class C        gS        @Displale        @Displ    class CancelMeetingTests {
        @Te        @Displayates    @Displ()                  @Displayates    @Displ()reimport tiimport java.ssimpNoimport reimport s            assimport stati    }
    @Nested
    @DisplayName("rejectMeetingRequest")
  ouimport reimport s            ail    @Nested
    @DisplayName("rejectMeetingReque()              ouimport reimport s            assimp1L  ng    @Nested
    @Display    }
}
